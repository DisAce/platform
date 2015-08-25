package org.coiol.platform.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseUserRole;
import org.coiol.platform.core.model.BaseUsers;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseUserRoleMapper;
import org.coiol.platform.dao.BaseUsersMapper;
import org.coiol.platform.service.BaseUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class BaseUsersServiceImpl implements BaseUsersService
{

  private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseUsersServiceImpl.class);
  @Autowired
  private BaseUsersMapper baseUsersMapper;

  @Autowired
  private BaseUserRoleMapper baseUserRoleMapper;

  @Value("${email.host:smtp.163.com}")
  private String emailHost;

  @Value("${email.account:test@whty.com.cn}")
  private String emailAccount;

  @Value("${email.password:test}")
  private String emailPassword;

  @Value("${system.url:http://localhost:8888/}")
  private String systemUrl;

  @Value("${reset.password:123456}")
  private String resetPassword;

  @Value("${limit.millis:3600000}")
  private Long millis;

  @Value("${limit.millis.text:一小时}")
  private String millisText;
  
  public static MethodUtil util = new MethodUtil();
  
  public String selectByBaseUser(Criteria criteria)
  {
    List<BaseUsers> list = this.baseUsersMapper.selectByExample(criteria);
    if ((null == list) || (list.size() != 1))
    {
      return "00";
    }
    BaseUsers dataBaseUser = (BaseUsers)list.get(0);

    if ((dataBaseUser.getErrorCount().shortValue() >= 3) && (compareTo(dataBaseUser.getLastLoginTime()))) {
    	return (new StringBuilder()).append("请你联系管理员，或者").append(millisText).append("之后再次尝试！").toString();
    }
    String password = DigestUtils.md5Hex(criteria.getAsString("passwordIn"));
    String passwordIn = encrypt(password, criteria.getAsString("account"));
    if (!passwordIn.equals(dataBaseUser.getPassword()))
    {
      return loginTimes(dataBaseUser, criteria);
    }

    BaseUsers updateUser = new BaseUsers();
    updateUser.setUserId(dataBaseUser.getUserId());
    updateUser.setErrorCount(Short.valueOf((short)0));
    updateUser.setLastLoginTime(new Date());
    updateUser.setLastLoginIp(criteria.getAsString("loginip"));
    this.baseUsersMapper.updateByPrimaryKeySelective(updateUser);

    criteria.put("baseUser", dataBaseUser);
    return "01";
  }

  public int countByExample(Criteria example) {
    int count = this.baseUsersMapper.countByExample(example);
    logger.debug("count: {}", Integer.valueOf(count));
    return count;
  }

  public BaseUsers selectByPrimaryKey(String userId) {
    return this.baseUsersMapper.selectByPrimaryKey(userId);
  }

  public List<BaseUsers> selectByExample(Criteria example) {
    return this.baseUsersMapper.selectByExample(example);
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String deleteByPrimaryKey(Criteria example) {
    int result = 0;

    this.baseUserRoleMapper.deleteByExample(example);
    String userId = example.getAsString("userId");
    result = this.baseUsersMapper.deleteByPrimaryKey(userId);
    return result > 0 ? "01" : "00";
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String updateUserPassword(Criteria example) {
    BaseUsers user = (BaseUsers)example.get("user");
    String oldPassword = example.getAsString("oldPassword");
    String userId = example.getAsString("userId");
    String newPassword = example.getAsString("newPassword");

    oldPassword = encrypt(oldPassword, user.getAccount());
    if ((!userId.equals(user.getUserId())) || (!oldPassword.equals(user.getPassword()))) {
      return "原密码不正确！请重新输入！";
    }

    BaseUsers baseUsers = new BaseUsers();
    baseUsers.setUserId(userId);
    String password = DigestUtils.md5Hex(newPassword);
    baseUsers.setPassword(encrypt(password, user.getAccount()));
    return this.baseUsersMapper.updateByPrimaryKeySelective(baseUsers) > 0 ? "01" : "00";
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String saveUser(Criteria example)
  {
    BaseUsers user = (BaseUsers)example.get("user");
    Collection<?> roleIds = (Collection<?>)example.get("roleIds");
    Criteria criteria = new Criteria();

    if (StringUtils.isNotBlank(user.getUserId()))
    {
      criteria.put("userId", user.getUserId());
    }
    criteria.put("account", user.getAccount());
    boolean exit = this.baseUsersMapper.countByExample(criteria) > 0;
    if (exit) {
      return "帐号已经被注册！请重新填写!";
    }
    int result = 0;
    if (StringUtils.isNotBlank(user.getPassword())){
    	  String password = DigestUtils.md5Hex(user.getPassword());
    	  user.setPassword(encrypt(password, user.getAccount()));
    }
  
    if (StringUtils.isBlank(user.getUserId()))
    {
      user.setUserId(util.getUid());
      user.setCreateTime(util.getDate(0,null));
      result = this.baseUsersMapper.insertSelective(user);
    }
    else {
      result = this.baseUsersMapper.updateByPrimaryKeySelective(user);
    }
    if (result == 0) {
      return "00";
    }

    criteria.clear();
    criteria.put("userId", user.getUserId());

    this.baseUserRoleMapper.deleteByExample(criteria);

    BaseUserRole role;
	for (Iterator<?> i$ = roleIds.iterator(); i$.hasNext(); baseUserRoleMapper.insert(role))
	{
		String roleId = (String)i$.next();
		role = new BaseUserRole();
		role.setRoleId(roleId);
		role.setId(util.getUid());
		role.setUserId(user.getUserId());
		role.setCreateTime(util.getDate(0,null));
	}
    return "01";
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String resetPwdByPrimaryKey(Criteria example) {
    String userId = example.getAsString("userId");
    BaseUsers oldUser = this.baseUsersMapper.selectByPrimaryKey(userId);
    if (oldUser == null) {
      return "没有找到该用户！";
    }
    BaseUsers user = new BaseUsers();
    user.setUserId(userId);
    user.setErrorCount(Short.valueOf((short)0));
    user.setPassword(encrypt(this.resetPassword, oldUser.getAccount()));
    return this.baseUsersMapper.updateByPrimaryKeySelective(user) > 0 ? "01" : "00";
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String updateByPrimaryKeySelective(BaseUsers user) {
    return this.baseUsersMapper.updateByPrimaryKeySelective(user) > 0 ? "01" : "00";
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String updatePassword(Criteria example) {
    BaseUsers user = this.baseUsersMapper.selectByPrimaryKey(example.getAsString("userId"));
    if (user == null) {
      return "00";
    }
    BaseUsers updateUser = new BaseUsers();
    updateUser.setUserId(user.getUserId());
    String password = DigestUtils.md5Hex(example.getAsString("password"));
    updateUser.setPassword(encrypt(password, user.getAccount()));
    return this.baseUsersMapper.updateByPrimaryKeySelective(updateUser) > 0 ? "01" : "00";
  }
  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String validateAccount(Criteria example) {
    return this.baseUsersMapper.countByExample(example) > 0 ? "00" : "01";
  }

  private String encrypt(String data, String salt)
  {
    return DigestUtils.md5Hex(data + "{" + salt + "}");
  }

  private String loginTimes(BaseUsers baseUsers, Criteria criteria)
  {
    String info = "";
    Short errorCount = baseUsers.getErrorCount();
    Date lastLoginTime = baseUsers.getLastLoginTime();
    if(lastLoginTime == null){
    	lastLoginTime = new Date();
    }
    if (!compareTo(lastLoginTime)) {
      errorCount = Short.valueOf((short)0);
    }
    switch (errorCount.shortValue())
    {
    case 0:
      info = "密码错误!你还有2次机会输入密码!";
      errorCount = Short.valueOf((short)(errorCount.shortValue() + 1));
      break;
    case 1:
      info = "密码错误!你还有1次机会输入密码!<br/>如果输入错误，帐户将被锁定不能登录！";
      errorCount = Short.valueOf((short)(errorCount.shortValue() + 1));
      break;
    case 2:
      info = "密码错误!你的帐户已经被锁定！<br/>请联系管理员！";
      errorCount = Short.valueOf((short)(errorCount.shortValue() + 1));
      break;
    }

    BaseUsers updateUser = new BaseUsers();
    updateUser.setUserId(baseUsers.getUserId());
    updateUser.setErrorCount(errorCount);
    updateUser.setLastLoginTime(new Date());
    updateUser.setLastLoginIp(criteria.getAsString("loginip"));
    this.baseUsersMapper.updateByPrimaryKeySelective(updateUser);
    return info;
  }

  private boolean compareTo(Date date)
  {
    Calendar c = Calendar.getInstance();
    long now = c.getTimeInMillis();
    c.setTime(date);
    long lastly = c.getTimeInMillis();

    return now - lastly <= this.millis.longValue();
  }

  @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
  public String findPassword(Criteria example) throws Exception {
    BaseUsers user = (BaseUsers)example.get("user");

    example.put("account", user.getAccount());
    example.put("email", user.getEmail());
    List<BaseUsers> list = this.baseUsersMapper.selectByExample(example);
    if ((null == list) || (list.size() != 1)) {
      return "请输入正确的帐号和其注册邮箱！";
    }
    BaseUsers dataBaseUser = (BaseUsers)list.get(0);

    String token = encrypt(RandomStringUtils.randomAlphanumeric(10), dataBaseUser.getAccount());
    
    BaseUsers updateUser = new BaseUsers();
    updateUser.setUserId(dataBaseUser.getUserId());
    updateUser.setLastLoginTime(new Date());
    updateUser.setPassword(token);
    this.baseUsersMapper.updateByPrimaryKeySelective(updateUser);

    String title = "亲爱的 " + dataBaseUser.getAccount() + "，请重新设置你的帐户密码！";
    String Password = encrypt(dataBaseUser.getUserId(), user.getAccount());
    String url = this.systemUrl + token.toUpperCase() + "/" + Password.toUpperCase();
    url = "<a href='" + url + "'>点此重置密码</a>";

    String body = "请点击下面链接，重新设置您的密码：<br/>" + url + " ,此链接一小时有效!<br/>" + "如果该链接无法点击，请直接拷贝以上网址到浏览器地址栏中访问。";
    execSend(dataBaseUser.getEmail(), title, body);
    return "01";
  }

  private boolean execSend(String address, String title, String body)
    throws Exception
  {
    Properties props = new Properties();

    props.put("mail.smtp.host", this.emailHost);
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props, new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(BaseUsersServiceImpl.this.emailAccount, BaseUsersServiceImpl.this.emailPassword);
      }
    });
    MimeMessage message = new MimeMessage(session);

    message.setSubject(title);

    message.setFrom(new InternetAddress(this.emailAccount));

    message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));

    message.setSentDate(new Date());

    Multipart mp = new MimeMultipart("related");

    BodyPart bodyPart = new MimeBodyPart();

    bodyPart.setContent(body, "text/html;charset=utf-8");

    mp.addBodyPart(bodyPart);

    message.setContent(mp);

    Transport.send(message);
    logger.info("向邮件地址:{}发送邮件成功！", address);
    return true;
  }
}