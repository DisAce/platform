package org.coiol.platform.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.coiol.platform.core.model.BaseLoginLog;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseLoginLogMapper;
import org.coiol.platform.service.BaseLoginLogService;
@Service
@Component
public class BaseLoginLogServiceImpl implements BaseLoginLogService {
	@Autowired
	private BaseLoginLogMapper baseLoginLogMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseLoginLogMapper.class);
	
	public BaseLoginLogServiceImpl(){
		
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String insert(BaseLoginLog loginLog) {
		int count = this.baseLoginLogMapper.insert(loginLog);
		if(count == 1){
			logger.info("日志写入成功！");
		}
		return "00";
	}
	
	public int countByExample(Criteria paramCriteria) {
		int count = baseLoginLogMapper.countByExample(paramCriteria);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}
	
	public BaseLoginLog selectByPrimaryKey(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BaseLoginLog> selectByExample(Criteria example) {
		return baseLoginLogMapper.selectByExample(example);
	}
	

}
