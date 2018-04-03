package com.ztz.service;

import com.ztz.entity.Collect;

public interface CollectService {
	public void collect(Collect collect);
	public void unCollect(Collect collect);
	public boolean isCollect(Collect collect);
}
