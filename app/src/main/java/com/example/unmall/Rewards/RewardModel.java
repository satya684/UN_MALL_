package com.example.unmall.Rewards;

public class RewardModel {

    private String rewardTitle;
    private String rewardExpiryDate;
    private String rewardCoupenBody;

    public RewardModel(String rewardTitle, String rewardExpiryDate, String rewardCoupenBody) {
        this.rewardTitle = rewardTitle;
        this.rewardExpiryDate = rewardExpiryDate;
        this.rewardCoupenBody = rewardCoupenBody;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public String getRewardExpiryDate() {
        return rewardExpiryDate;
    }

    public void setRewardExpiryDate(String rewardExpiryDate) {
        this.rewardExpiryDate = rewardExpiryDate;
    }

    public String getRewardCoupenBody() {
        return rewardCoupenBody;
    }

    public void setRewardCoupenBody(String rewardCoupenBody) {
        this.rewardCoupenBody = rewardCoupenBody;
    }
}
