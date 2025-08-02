package entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@ToString
public class EmployeeAccout {

    
    String username;
    Integer roleId;
    boolean hasAccount;

    public boolean hasAccount() {
        return hasAccount;
    }
}
