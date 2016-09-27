package cn.fql.fishbone.model.annotation;

import cn.fql.fishbone.model.enums.OperationLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块操作管理注解,用于Controller中的方法
 * @author fuquanlin
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {

	/**
	 * 操作类型，详见 {@link OperationLogType}
	 * */
	public OperationLogType value();
	
	/**
	 * 操作描述
	 * */
	public String description() default "";


	/**
	 * 指定操作的selectOne方法标记名
	 * @return
     */
	public String selectOne() default "";
	
	/**
	 * 是否不记录日志，默认false
	 * */
	public boolean ignoreLog() default false;
}
