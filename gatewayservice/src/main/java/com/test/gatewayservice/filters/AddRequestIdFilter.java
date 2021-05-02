package com.test.gatewayservice.filters;

import java.util.UUID;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/*
 * Sinh ra request id cho mỗi thằng request
 */
public class AddRequestIdFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {// xử lý các công việc của th filter 
		// TODO Auto-generated method stub
		RequestContext context = RequestContext.getCurrentContext();
		String requestId = UUID.randomUUID().toString();
		context.addZuulRequestHeader("requestId", requestId);
		return null;
	}

	@Override
	public String filterType() { // có  4 loại filter khác nhau
		// TODO Auto-generated method stub
		//pre : filter xảy ra trước khi request đươc gửi đến gateway service
		//route: routing  điều hướng các request
		//post: filter xảy ra sau khi request đã đi qua gateway servce
		//error: để xử lý error trong trường hợp mà có error xảy ra với các filter còn lại
		
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		//quản lý thứ tự 1 thằng filter trong hợp có nhiều thằng filter cùng loại 
		//filter nào có thứ tự nhỏ hơn thực hiện trước
		// TODO Auto-generated method stub
		return 0; //trong demo này chỉ 1 filter duy nhất nên hàm nay giữ nguyên
	}

}
