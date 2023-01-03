package com.allmedia.portal.facebook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageMapper {

}
