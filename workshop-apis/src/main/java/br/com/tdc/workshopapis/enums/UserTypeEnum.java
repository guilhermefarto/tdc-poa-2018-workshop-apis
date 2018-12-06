package br.com.tdc.workshopapis.enums;

import java.beans.PropertyEditorSupport;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserTypeEnum {

	ADMIN("admin"), USER("user");

	private final String value;

	private UserTypeEnum(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public static UserTypeEnum findByValue(String value) {
		for (UserTypeEnum item : UserTypeEnum.values()) {
			if (value.equalsIgnoreCase(item.getValue())) {
				return item;
			}
		}

		return null;
	}

	public static class UserTypeEnumConverter extends PropertyEditorSupport {

		@Override
		public void setAsText(String value) throws IllegalArgumentException {
			setValue(UserTypeEnum.findByValue(value));
		}
	}
}
