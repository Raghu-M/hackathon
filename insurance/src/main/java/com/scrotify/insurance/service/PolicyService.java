package com.scrotify.insurance.service;

import java.util.List;

import com.scrotify.insurance.dto.PolicyResponseDto;
import com.scrotify.insurance.dto.TrendingResponseDto;

/**
 * This service interface has a method declarations of policies.
 * 
 * @Api getTrendingPolicies which is used to get the trending policies
 *      considering the last ten purchase.
 * 
 * @author Anisha R
 * @author Raghu M.
 * 
 */
public interface PolicyService {

	/**
	 * This API is used to get the trending policies considering the last ten
	 * purchase.
	 * 
	 * @return This returns the list of trending policies which contain policy name
	 *         and number of policies purchased.
	 * 
	 * @author Raghu M
	 */
	List<TrendingResponseDto> getTrendingPolicies();

	List<PolicyResponseDto> listAllPolicies();

}
