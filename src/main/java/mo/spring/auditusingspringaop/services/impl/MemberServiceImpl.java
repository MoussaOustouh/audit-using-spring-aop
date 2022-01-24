package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.entities.Member;

import mo.spring.auditusingspringaop.exceptions.NotFoundException;
import mo.spring.auditusingspringaop.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaop.repositories.MemberRepository;
import mo.spring.auditusingspringaop.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements IMemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> findOne(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return memberOptional;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member entity) {
        return null;
    }

    @Override
    public Member update(Member entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
