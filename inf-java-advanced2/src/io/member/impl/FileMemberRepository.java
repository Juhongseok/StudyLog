package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-data.dat";
    private static final String DELIMITER = ",";

    @Override
    public void add(Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, UTF_8, true))) {
            writer.write(member.getId() + DELIMITER + member.getName() + DELIMITER + member.getAge());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH, UTF_8))) {
            List<Member> members = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] memberData = line.split(DELIMITER);
                members.add(new Member(memberData[0], memberData[1], Integer.parseInt(memberData[2])));
            }

            return members;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
