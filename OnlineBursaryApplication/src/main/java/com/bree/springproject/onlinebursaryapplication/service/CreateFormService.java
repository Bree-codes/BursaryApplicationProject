package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.SectionATable;
import com.bree.springproject.onlinebursaryapplication.repository.SectionATableRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
public class CreateFormService {

    @Autowired
    SectionATableRepository sectionATableRepository;

    public ResponseEntity<String> createSectionA(Map<String, String> sectionA, String month, Long userId) {

        List<String> fields = new ArrayList<>(sectionA.keySet());
        List<SectionATable> sectionAColumns = new ArrayList<>();

        for(String field : fields)
        {
            SectionATable sectionAColumn = new SectionATable();

            sectionAColumn.setUserId(userId);
            sectionAColumn.setBursaryMonth(month);
            sectionAColumn.setField(field);
            sectionAColumn.setType(sectionA.get(field));

            sectionAColumns.add(sectionAColumn);
        }

        //batch update.
        sectionATableRepository.saveAll(sectionAColumns);

        return new ResponseEntity<>("Save Successfully", HttpStatus.CREATED);
    }
}
