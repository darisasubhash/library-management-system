package com.library.repository;

import com.library.model.IssueRecord;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private static List<IssueRecord> records =new ArrayList<>();

    public void addRecord(IssueRecord record){
        records.add(record);
    }
    public IssueRecord findActiveRecordByBookId(int bookId){
        for(IssueRecord record:records){
            if(record.getBook().getId()==bookId){
                return record;
            }
        }
        return null;
    }

    public List<IssueRecord> getRecords() {
        return records;
    }

    public IssueRecord getActiveIssueByUserId(int userId) {

        for (IssueRecord record : records) {
            if (record.getUser().getUserId() == userId && record.getReturnDate()==null) {
                return record;
            }
        }
        return null;
    }
}
