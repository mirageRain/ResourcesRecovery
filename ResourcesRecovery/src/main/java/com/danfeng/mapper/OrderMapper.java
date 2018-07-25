package com.danfeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;

import com.danfeng.entity.OrderEntity;

public interface OrderMapper {

	@Select("SELECT * FROM order_ WHERE enable=1 and order_id = #{orderId}  ")
	@Results({ @Result(id = true, property = "orderId", column = "order_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "describe", column = "desc_"), @Result(property = "state", column = "state"),
			@Result(property = "applyTime", column = "apply_time"),
			@Result(property = "accomplishTime", column = "accomplish_time"),
			@Result(property = "collectTimeType", column = "collect_time_type"),
			@Result(property = "expectTime", column = "expect_time"),
			@Result(property = "collectItem", column = "order_id", javaType = List.class, many = @Many(select = "com.danfeng.mapper.CollectItemMapper.selectCollectItemByOrderId")) })
	OrderEntity getOrderByOrderId(Integer orderId);
	
	@Select("SELECT * FROM order_ WHERE enable=1 order by state,order_id desc")
	@Results({ @Result(id = true, property = "orderId", column = "order_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "describe", column = "desc_"), @Result(property = "state", column = "state"),
			@Result(property = "applyTime", column = "apply_time"),
			@Result(property = "accomplishTime", column = "accomplish_time"),
			@Result(property = "collectTimeType", column = "collect_time_type"),
			@Result(property = "expectTime", column = "expect_time"),
			@Result(property = "collectItem", column = "order_id", javaType = List.class, many = @Many(select = "com.danfeng.mapper.CollectItemMapper.selectCollectItemByOrderId")) })
	List<OrderEntity> getAllOrder();
	
	@Select("SELECT * FROM order_ WHERE enable=1 and user_id = #{userId} order by order_id desc,state")
	@Results({ @Result(id = true, property = "orderId", column = "order_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "describe", column = "desc_"), @Result(property = "state", column = "state"),
			@Result(property = "applyTime", column = "apply_time"),
			@Result(property = "accomplishTime", column = "accomplish_time"),
			@Result(property = "collectTimeType", column = "collect_time_type"),
			@Result(property = "expectTime", column = "expect_time"),
			@Result(property = "collectItem", column = "order_id", javaType = List.class, many = @Many(select = "com.danfeng.mapper.CollectItemMapper.selectCollectItemByOrderId")) })
	List<OrderEntity> getOrderByUserId(Integer userId);
	
	@Select("SELECT count(*) FROM order_ WHERE enable=1 and user_id = #{userId} and(state=1 or state =2)")
	int getOrderCount(Integer userId);
	
	@Select("SELECT count(*) FROM order_ WHERE enable=1 and user_id = #{userId} and state=1")
	int getBusinessWaitTakingOrderCount(Integer userId);
	
	@Select("SELECT count(*) FROM order_ WHERE enable=1 and user_id = #{userId} and state=2")
	int getBusinessWaitHomeOrderCount(Integer userId);

	@Insert("INSERT INTO order_(user_id,collect_address_id,desc_,state,apply_time,collect_time_type,expect_time) "
			+ "VALUES(#{userId}, #{collectAddressId}, #{describe}, #{state}, #{applyTime} , #{collectTimeType} ,#{expectTime} )")
	@Options(useGeneratedKeys = true, keyProperty = "orderId")
	int insert(OrderEntity orderEntity);

	@Update("UPDATE order_ SET "  + "collect_address_id =#{collectAddressId} ,"
			+ "desc_=#{describe} ," + "state=#{state} ,"
			+ "collect_time_type=#{collectTimeType} ," + " expect_time=#{expectTime} where user_id=#{userId} and order_id=#{orderId} and enable = 1")
	int update(OrderEntity orderEntity);
	
	@Update("UPDATE order_ SET "  + "state=2"
			+ "  where order_id=#{orderId} and enable = 1")
	int takingOrder(OrderEntity orderEntity);
	
	@Update("UPDATE order_ SET "  + "state=3 ,"+"accomplish_time=#{accomplishTime}"
			+ "  where order_id=#{orderId} and enable = 1")
	int accomplishOrder(OrderEntity orderEntity);

	@Delete("UPDATE order_ SET enable = 0  WHERE user_id=#{userId} and order_id =#{orderId}")
	int delete(OrderEntity orderEntity);

}