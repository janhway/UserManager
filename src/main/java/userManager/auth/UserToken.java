package userManager.auth;

import org.springframework.util.Base64Utils;

public class UserToken {

	final static String splitWd = ":";

	private long userId;
	private long startMillSeconds;
	private long endMillSecondds;

	public UserToken() {

	}

	public UserToken(long userId, long startMillSeconds, long endMillSecondds) {
		this.userId = userId;
		this.startMillSeconds = startMillSeconds;
		this.endMillSecondds = endMillSecondds;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStartMillSeconds() {
		return startMillSeconds;
	}

	public void setStartMillSeconds(long startMillSeconds) {
		this.startMillSeconds = startMillSeconds;
	}

	public long getEndMillSecondds() {
		return endMillSecondds;
	}

	public void setEndMillSecondds(long endMillSecondds) {
		this.endMillSecondds = endMillSecondds;
	}

	public String encode() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(userId).append(splitWd).append(startMillSeconds)
				.append(splitWd).append(endMillSecondds);
		return Base64Utils.encodeToString(sb.toString().getBytes());
	}

	public static UserToken decode(String encodedToken) {
		byte[] decodedByte = Base64Utils.decodeFromString(encodedToken);
		String decodedString = new String(decodedByte);
		String[] decodedPart = decodedString.split(splitWd);
		if (decodedPart == null || decodedPart.length != 3) {
			return null;
		}

		return new UserToken(Long.valueOf(decodedPart[0]),
				Long.valueOf(decodedPart[1]), Long.valueOf(decodedPart[2]));
	}
	
	public static String makeEncodeToken(long userId){
		long cur = System.currentTimeMillis();
		UserToken ut = new UserToken(userId, cur, cur + 3600 * 1000);
		return ut.encode();
	}

	public static void main(String[] args) {
		long cur = System.currentTimeMillis();

		UserToken ut = new UserToken(10, cur, cur + 3600 * 1000);
		String encodedToken = ut.encode();
		System.out.println("encodedToken="+encodedToken);
		System.out.println("cur="+cur);
		
		UserToken out = UserToken.decode(encodedToken);
		if (ut.getUserId() != out.getUserId()
				|| ut.getStartMillSeconds() != out.getStartMillSeconds()
				|| ut.getEndMillSecondds() != out.getEndMillSecondds()) {
			System.out.println("Encode or decode error!");
		}
		
		System.out.println("out.getUserId()="+out.getUserId());
		System.out.println("out.getStartMillSeconds()="+out.getStartMillSeconds());
		System.out.println("out.getEndMillSecondds()="+out.getEndMillSecondds());
	}

}
