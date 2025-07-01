package service;

import dao.VoteApprovalDAO;
import entity.VoteApproval;
import java.util.ArrayList;
import java.util.List;

public class VoteApprovalService implements VoteApprovalDAO {
    
    private List<VoteApproval> voteApproval = new ArrayList<>();

    public List<VoteApproval> getAllVoteApprovalService() {
        return voteApproval = getAllVoteApproval();
    }
    
}
