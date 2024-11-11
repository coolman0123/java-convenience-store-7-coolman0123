package store.service;

public class MembershipService {

    public int goMembershipDiscount(String input, int rp) {
        if (input.equals("Y")) {
            int discountRate = (int) (rp * 0.30);
            if (discountRate > 8000) {
                return 8000;
            }
            return discountRate;
        }
        return 0;
    }
}
