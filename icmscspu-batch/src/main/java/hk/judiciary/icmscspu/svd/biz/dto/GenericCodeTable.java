package hk.judiciary.icmscspu.svd.biz.dto;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GenericCodeTable {
	String type();
	String label() default "";
}
