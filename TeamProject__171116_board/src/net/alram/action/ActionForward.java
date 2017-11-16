package net.alram.action;

public class ActionForward {
	// 이동정보를 저장 (이동방식정하기, 이동경로 )
			// 이동방식  sendRedirect -> true  약속
			//       forward      -> false 약속
			private boolean isRedirect;
			private String path;
			// set  get 메서드 만들기  alt shift s   r
			public boolean isRedirect() {
				return isRedirect;
			}
			public void setRedirect(boolean isRedirect) {
				this.isRedirect = isRedirect;
			}
			public String getPath() {
				return path;
			}
			public void setPath(String path) {
				this.path = path;
			}
}
