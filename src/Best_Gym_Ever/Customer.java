package Best_Gym_Ever;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
    private String name;
    private String personalNumber;
    private LocalDate joinDate;


    public Customer(String name, String personalNumber,LocalDate joinDate){
        this.name = name;
        this.personalNumber = personalNumber;
        this.joinDate = joinDate;

    }

    public String getName() {
        return name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    @Override
    public String toString() {
        return name+personalNumber+joinDate;
    }
    public MemberStatus getStatus(){
        LocalDate today = LocalDate.now();
        Period difference = Period.between(joinDate, today);
        if(difference.getYears() < 1) {
            return MemberStatus.MEMBER;
        } else {
            return MemberStatus.EX_MEMBER;
        }
    }
}
