
package org.coiol.platform.common.mybatis.dialect;

public class MySQLDialect extends Dialect
{

	public MySQLDialect()
	{
	}

	public boolean supportsLimitOffset()
	{
		return true;
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		if (offset > 0)
			return (new StringBuilder()).append(sql).append(" limit ").append(offsetPlaceholder).append(",").append(limitPlaceholder).toString();
		else
			return (new StringBuilder()).append(sql).append(" limit ").append(limitPlaceholder).toString();
	}
}
