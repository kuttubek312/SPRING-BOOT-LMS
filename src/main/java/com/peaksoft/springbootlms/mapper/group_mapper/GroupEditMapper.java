package com.peaksoft.springbootlms.mapper.group_mapper;

import com.peaksoft.springbootlms.dto.group_dto.GroupRequest;
import com.peaksoft.springbootlms.model.Group;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GroupEditMapper {

    public Group create(GroupRequest request) {
        if (request == null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setCreated(LocalDate.now());
        group.setActive(true);
        return group;
    }

    public void update(Group group, GroupRequest groupRequest) {
        group.setGroupName(groupRequest.getGroupName());
        group.setGroupName(groupRequest.getDateOfStart());
        group.setDateOfFinish(groupRequest.getDateOfFinish());
    }
}
