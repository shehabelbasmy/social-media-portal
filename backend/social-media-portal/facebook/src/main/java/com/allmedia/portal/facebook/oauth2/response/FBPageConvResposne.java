package com.allmedia.portal.facebook.oauth2.response;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FBPageConvResposne {
	
	@JsonProperty("data")
	private List<Data> data;
	
	@Setter
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Data{
		
		@JsonProperty("id")
		private String id;
		
		@JsonProperty("updated_time")
		private ZonedDateTime updatedTime;
		
		@JsonProperty("participants")
		private Participants participants;
		
		@Builder
		@AllArgsConstructor
		@NoArgsConstructor
		@Setter
		@Getter
		public static class Participants{
			
			@JsonProperty("data")
			private List<Data1> data;

			@Builder
			@AllArgsConstructor
			@NoArgsConstructor
			@Setter
			@Getter
			public static class Data1{
				
				@JsonProperty("name")
				private String name;
			}
			
		}
		
	}
}



















































//	@SuppressWarnings("unchecked")
//	@JsonProperty("participants")
//	private void unpackNested(Map<String, Object> data) {
//		this.id = getNestedValue(data, "data");
//		this.id = data.get("id").toString();
//	}
//	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private <T> T getNestedValue(Map map, String... keys) {
//	    Object value = map;
//
//	    for (String key : keys) {
//	        value = ((Map) value).get(key);
//	    }
//
//	    return (T) value;
//	}