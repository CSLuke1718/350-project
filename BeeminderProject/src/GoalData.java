/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CSLuk
 */
public class GoalData {
    private String slug; //goal name
    private long losedate; //unix time when the goal will derail
    private String safesum; //how many units needed to meet losedate deadline
    
    GoalData() {
    }
    
    GoalData(String s, long l, String ss) {
        slug = s;
        losedate = l;
        safesum = ss;
    }
    
    
    public void setSlug(String s) {
        slug = s;
    }
    
    public void setDueDate(long l) {
        losedate = l;
    }
    
    public void setSafetyText(String ss) {
        safesum = ss;
    }
    
    
    public String getSlug() {
        return slug;
    }
    
    public long getDueDate() {
        return losedate;
    }
    
    public String getSafetyText() {
        return safesum;
    }
    
}
