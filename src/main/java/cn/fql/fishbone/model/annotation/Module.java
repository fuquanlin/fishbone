package cn.fql.fishbone.model.annotation;

import java.lang.annotation.*;

/**
 * 模块管理注解,用于Controller
 * @author fuquanlin
 * */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

	/**
	 * 操作描述
	 * */
	public String value() default "";
	
	/**
	 * 是否不记录日志，默认false
	 * */
	public boolean ignoreLog() default false;
}
