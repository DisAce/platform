

package org.coiol.platform.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.coiol.platform.core.jackjson.JackJson;
import org.coiol.platform.core.log.PlatFormLogger;
import org.coiol.platform.core.log.PlatFormLoggerFactory;
import org.coiol.platform.core.model.BaseFields;
import org.coiol.platform.core.model.Criteria;
import org.coiol.platform.dao.BaseFieldsMapper;
import org.coiol.platform.service.BaseFieldsService;
@Service
@Component
public class BaseFieldsServiceImpl
	implements BaseFieldsService
{
	@Autowired
	private BaseFieldsMapper baseFieldsMapper;
	private static final PlatFormLogger logger = PlatFormLoggerFactory.getPlatFormLogger(BaseFieldsServiceImpl.class);

	public int countByExample(Criteria example)
	{
		int count = baseFieldsMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseFields selectByPrimaryKey(String fieldId)
	{
		return baseFieldsMapper.selectByPrimaryKey(fieldId);
	}

	public List<BaseFields> selectByExample(Criteria example)
	{
		return baseFieldsMapper.selectByExample(example);
	}

	public HashMap<String,String> selectAllByExample(Criteria example)
	{
		List<BaseFields> list = baseFieldsMapper.selectByExample(example);
		HashMap<String, LinkedHashMap<String, String>> all = new HashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> part = null;
		for (int i = 0; i < list.size(); i++)
		{
			BaseFields baseFields = list.get(i);
			String key = baseFields.getField();
			if (all.containsKey(key))
			{
				part = all.get(key);
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
			} else
			{
				part = new LinkedHashMap<String, String>();
				part.put(baseFields.getValueField(), baseFields.getDisplayField());
				all.put(key, part);
			}
		}

		part = new LinkedHashMap<String, String>();
		logger.info("开始读取系统默认配置");
		String key;
		String val;
		for (Iterator<?> i$ = all.entrySet().iterator(); i$.hasNext(); part.put(key, val))
		{
			@SuppressWarnings("rawtypes")
			Entry entry = (java.util.Map.Entry)i$.next();
			key = (String)entry.getKey();
			HashMap<?, ?> value = (HashMap<?, ?>)entry.getValue();
			val = JackJson.fromObjectToJson(value).replaceAll("\\'", "\\\\'");
			logger.info(val);
		}

		logger.info("结束读取系统默认配置");
		return part;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveFields(Criteria example)
	{
		BaseFields fields = (BaseFields)example.get("fields");
		int result = 0;
		if (fields.getFieldId() == null)
			result = baseFieldsMapper.insertSelective(fields);
		else
			result = baseFieldsMapper.updateByPrimaryKeySelective(fields);
		return result <= 0 ? "00" : "01";
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String delete(Criteria example)
	{
		String fieldId = example.getAsString("fieldId");
		int result = 0;
		result = baseFieldsMapper.deleteByPrimaryKey(fieldId);
		return result <= 0 ? "00" : "01";
	}

}
