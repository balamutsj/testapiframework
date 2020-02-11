public class ResponseBodyModel {
    public class TokenData {
        private boolean success;
        private String token;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public class EmailList {
        private String emailId;
        private Sender sender;
        private String subject;
        private boolean isRead;
        private int timestamp;
    }

    public class Sender {
        private String address;
        private Sender name;
    }

}
