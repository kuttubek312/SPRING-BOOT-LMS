package com.peaksoft.springbootlms.mapper.group_mapper;

import com.peaksoft.springbootlms.dto.group_dto.GroupResponse;
import com.peaksoft.springbootlms.model.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupViewMapper {

    public GroupResponse viewGroup(Group group) {
        if (group == null) {
            return null;
        }
        GroupResponse response = new GroupResponse();
        response.setId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setDateOfStart(group.getDateOfStart());
        response.setDateOfFinish(group.getDateOfFinish());
        response.setActive(group.isActive());
        response.setCreated(group.getCreated());
        return response;
    }

    public List<GroupResponse> view(List<Group> groups) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group : groups) {
            responses.add(viewGroup(group));
        }
        return responses;
    }
}
