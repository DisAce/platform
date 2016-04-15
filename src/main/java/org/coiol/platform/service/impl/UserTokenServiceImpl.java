package org.coiol.platform.service.impl;

import java.util.List;

import org.coiol.platform.common.utils.MethodUtil;
import org.coiol.platform.core.constant.ResultCode;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.core.model.UserToken;
import org.coiol.platform.dao.UserTokenMapper;
import org.coiol.platform.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTokenServiceImpl implements UserTokenService {

	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(UserTokenServiceImpl.class);

	@Autowired
	private UserTokenMapper userTokenMapper;

	public static MethodUtil util = new MethodUtil();

	@Override
	public int countByExample(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserToken selectByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserToken> selectByExample(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatePassword(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPassword(Criteria paramCriteria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPwdByPrimaryKey(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteByPrimaryKey(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectByBaseUser(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateAccount(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUserPassword(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * add token
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String saveUserToken(Criteria paramCriteria) {
		if (logger.isDebugEnabled()) {
			logger.debug("enter saveUserToken action ; req = " + paramCriteria.toString());
		}
		int result = ResultCode.NULL;
		UserToken ut = (UserToken) paramCriteria.get("userToken");
		result = userTokenMapper.insertSelective(ut);
		if (result == ResultCode.NULL) {
			return ResultCode.FALSE;
		}
		return ResultCode.TRUE;
	}

	@Override
	public String updateByPrimaryKeySelective(UserToken paramUserToken) {
		// TODO Auto-generated method stub
		return null;
	}

}