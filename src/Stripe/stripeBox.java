package Stripe;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class stripeBox {
    static double stripe_fee = 0.02;
    static HashMap<String, Double> cardfee = new HashMap<>();
    static HashMap<String, Double> payout_map = new HashMap<>(); //merchantid -> amount
    static HashMap<String, ChargeRecord> charge_map = new HashMap<>(); //chargeId, chargeRecord

    public static void main(String[] args) {
        String[] actions = {
                "2",
                "visa 2.0", "mastercard 3.0",
                "8",
                "/charge?merchant_id=m001&charge_id=c001&amount=1000&network=visa",
                "/charge?merchant_id=m001&charge_id=c002&amount=1000&network=mastercard",
                "/confirm?charge_id=c001",
                "/confirm?charge_id=c002",
                "/payout?merchant_id=m001",
                "/charge?merchant_id=m001&charge_id=c003&amount=1000&network=visa",
                "/confirm?charge_id=c003",
                "/payout?merchant_id=m001"};
        stripeBox(Arrays.asList(actions));
    }

    public static void stripeBox(List<String> actions) {
        int n = Integer.valueOf(actions.get(0));
        for (int i = 1; i < 1 + n; i++) {
            String ca = actions.get(i);
            String[] c = ca.split("\\s+");
            cardfee.put(c[0], Double.valueOf(c[1]) / 100);
        }

        for (int i = n + 2; i < actions.size(); i++) {
            String str = actions.get(i);
            String mID = "";
            String cID = "";
            long am = 0;
            double netFee = 0;
            String[] url = str.split("\\?");
            String op = url[0].substring(1);
            String act = url[1];
            List<NameValuePair> args = URLEncodedUtils.parse(act, Charset.defaultCharset());
            for (NameValuePair arg : args) {
                String name = arg.getName();
                if (name.equals("network")) {
                    netFee = cardfee.get(arg.getValue());
                } else if (name.equals("amount")) {
                    am = Long.valueOf(arg.getValue());
                } else if (name.equals("merchant_id")) {
                    mID = arg.getValue();
                } else if (name.equals("charge_id")) {
                    cID = arg.getValue();
                }
            }
            if (op.equals("charge")) charge(netFee, am, mID, cID);
            else if (op.equals("refund")) refund(cID);
            else if (op.equals("confirm")) confirm(cID);
            else System.out.println(payout(mID));
        }
    }

    // /charge?network=visa&amount=100&merchant_id=m001&charge_id=c001
    public static void charge(double netFee, long amount, String mID, String cID) {
        ChargeRecord cr = new ChargeRecord(amount, netFee, mID);
        charge_map.put(cID, cr);
    }

    // /refund?charge_id=c001
    public static void refund(String cID) {
        ChargeRecord cr = charge_map.get(cID);
        double amount = payout_map.getOrDefault(cr.mID, 0.0);
        amount -= cr.amount * cr.netFee;
        payout_map.put(cr.mID, amount);
        charge_map.remove(cID);
    }

    // /confirm?charge_id=c002"
    public static void confirm(String cID) {
        ChargeRecord cr = charge_map.get(cID);
        double amount = payout_map.getOrDefault(cr.mID, 0.0);
        amount += cr.amount * (1 - cr.netFee - stripe_fee);
        payout_map.put(cr.mID, amount);
        charge_map.remove(cID);
    }

    // /payout?merchant_id=m001
    public static String payout(String mID) {
        long finalAmount = 0L;
        if (payout_map.containsKey(mID)) finalAmount = (long) Math.ceil(payout_map.get(mID));
        payout_map.put(mID, 0.0);
        return mID + ", " + finalAmount;
    }

    public static class ChargeRecord {
        long amount;
        double netFee;
        String mID;

        ChargeRecord(long amount, double netFee, String mId) {
            this.amount = amount;
            this.netFee = netFee;
            this.mID = mId;
        }
    }

}