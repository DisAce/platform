// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DerbyDialect.java

package org.coiol.platform.common.mybatis.dialect;


public class DerbyDialect extends Dialect
{

	public DerbyDialect()
	{
	}

	public boolean supportsLimit()
	{
		return false;
	}

	public boolean supportsLimitOffset()
	{
		return false;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		throw new UnsupportedOperationException("paged queries not supported");
	}
}
