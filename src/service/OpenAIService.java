/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ChatMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Duc Pham Ngoc
 */

//MỌI CONFIGURATION ĐỀU DỰA TRÊN DOCS CỦA OPENAI API

public class OpenAIService {
    private static String OPENAI_API_URL;
    private static String API_KEY;
    private static String MODEL;
    private static int MAX_TOKENS;
    private static double TEMPERATURE; // độ sáng tạo
    
    static{
        loadConfiguration();
    }
    
    private static void loadConfiguration(){
        Properties props = new Properties(); //object này để đọc file config
        
        try (InputStream input = OpenAIService.class.getClassLoader().getResourceAsStream("config/openai.properties")){
            props.load(input);
            // doc gia tri
            OPENAI_API_URL = props.getProperty("openai.api.url");
            API_KEY = props.getProperty("openai.api.key");
            MODEL = props.getProperty("openai.model");
            // ep kieu string sang so nguyen
            MAX_TOKENS = Integer.parseInt(props.getProperty("openai.max.tokens"));
            // ep kieu string sang so thuc
            TEMPERATURE = Double.parseDouble(props.getProperty("openai.temperature"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // method gui tin nhan den API
    public String sendMessage(String userMessage, List<ChatMessage> conversationHistory){
        try{
            URL url = new URL(OPENAI_API_URL);
            // mở kết nối http đến API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // cấu hình request method và header
            connection.setRequestMethod("POST"); //thiết lập phương thức http là POST vì Openai API yêu cầu sử dụng POST method để gửi dữ liệu
            connection.setRequestProperty("Content-Type", "application/json"); //định dạng dữ liệu gửi là JSON để thông báo cho SV biết dữ liệu gửi đi có dạng này
            connection.setRequestProperty("Authorization", "Bearer "+ API_KEY); //xác thực bằng Bearer token vì Openai sử dụng Bearer token để xác định người dùng và kiểm tra quyền truy cập
            connection.setDoOutput(true); //cho phép ghi dữ liệu ra output stream vì mặc định HttpURLConnection không cho phép output
            connection.setConnectTimeout(30000); //thiết lập thời gian chờ tối đa là 30s để kết nối
            connection.setReadTimeout(60000); //thiết lập thời gian chờ tối đa để đọc data từ SV
            
            //String requestBody = buildRequestBody(userMessage, conversationHistory);
            //System.out.println(requestBody);
            
            //đọc response từ SV
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            
            //nếu request thành công thì thực thi code trong if (200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //đọc và parse response
                String responseStr = readResponseBody(connection.getInputStream());
                System.out.println(responseStr);
                return parseResponse(responseStr);
            } else {
                String errorStr = readResponseBody(connection.getErrorStream());
                System.err.println("API LOI (Code " + responseCode + "): " + errorStr);
                return getErrorMessage(responseCode);
            }
        }catch(Exception e){
            e.printStackTrace();
            return "Lỗi, không thể kết nối! Lỗi: " + e.getMessage();
        }
    }
    
    //method này dùng để đọc toàn bộ nội dung từ inputStream và chuyển sang String
    private String readResponseBody(InputStream inputStream) throws IOException {
        //InputStreamReader để chuyển đổi byte stream thành character stream
        //StandardCharsets.UTF_8 để đọc đúng ký tự tiếng Việt và emoji
        try (BufferedReader br =new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            //cái này để lưu từng lines đọc được từ stream
            String line;
            
            //vòng lặp while ày để đọc từng dòng cho tới khi null (hết dữ liệu)
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
    
    
    private String parseResponse(String jsonResponse) {
        try {
            //tìm "content": trong JSON
            String searchPattern = "\"content\":\"";
            int startIndex = jsonResponse.indexOf(searchPattern);

            if (startIndex == -1) {
                return "Không tìm thấy nội dung phản hồi từ AI.";
            }
            int contentStart = startIndex + searchPattern.length();
            // tìm dấu " kết thúc
            int contentEnd = findClosingQuote(jsonResponse, contentStart);
            if (contentEnd == -1) {
                return "Không thể phân tích phản hồi từ AI.";
            }
            String content = jsonResponse.substring(contentStart, contentEnd);
            String result = content;

            return result.trim().isEmpty() ? "Phản hồi từ AI trống." : result;

        } catch (Exception e) {
            return "Lỗi khi xử lý phản hồi từ AI: " + e.getMessage();
        }
    }

    private int findClosingQuote(String text, int startPos) {
        for (int i = startPos; i < text.length(); i++) {
            if (text.charAt(i) == '"') {
                // Kiểm tra xem dấu " này có bị escape không
                if (i == 0 || text.charAt(i - 1) != '\\') {
                    return i; // Tìm thấy dấu " không bị escape
                }
            }
        }
        return -1; // Không tìm thấy
    }
    
    private String getErrorMessage(int responseCode) {
        switch (responseCode) {
            case 401: 
                return "Lỗi xác thực API key. Vui lòng kiểm tra cấu hình API key.";
            case 429: 
                return "Đã vượt quá giới hạn API. Vui lòng thử lại sau.";
            case 400: 
                return "Yêu cầu không hợp lệ. Vui lòng thử lại với tin nhắn khác.";
            case 500:
                return "Lỗi server OpenAI. Vui lòng thử lại sau.";
            case 503: 
                return "Dịch vụ OpenAI tạm thời không khả dụng. Vui lòng thử lại sau.";
            default: 
                return "Lỗi từ OpenAI API (Code: "+responseCode+"). Vui lòng thử lại sau.";
        }
    }
}
