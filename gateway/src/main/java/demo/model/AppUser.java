package demo.model;

public class AppUser {
  private Long userId;
  private String userName;
  private String encrytedPassword;
  private String role;

  public AppUser() {

  }

  public AppUser(Long userId, String userName, String encrytedPassword) {
    this.userId = userId;
    this.userName = userName;
    this.encrytedPassword = encrytedPassword;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEncrytedPassword() {
    return encrytedPassword;
  }

  public void setEncrytedPassword(String encrytedPassword) {
    this.encrytedPassword = encrytedPassword;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return this.userName + "/" + this.encrytedPassword;
  }
}
