package com.scrotify.insurance.service;

import java.util.List;

import com.scrotify.insurance.entity.Policy;

public interface PolicyHolderService {

	public List<Policy> viewMyPolicy(Integer policyId);

}
