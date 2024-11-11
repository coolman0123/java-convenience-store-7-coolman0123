package store.service;

public class MembershipService {


    public int goMembershipDiscount(String input, int rp){
        if(input.equals("Y")){
            int discoutRp = (int) (rp * 0.30);

            return discoutRp;
        }
        return 0;

    }


}
