package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.entities.Member;

import mo.spring.auditusingspringaop.exceptions.NotFoundException;
import mo.spring.auditusingspringaop.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaop.repositories.MemberRepository;
import mo.spring.auditusingspringaop.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Member { id : "+id+" }");
        }
        return memberOptional;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member entity) {
        return this.memberRepository.save(entity);
    }

    @Override
    public Member update(Member entity) {
        return this.memberRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        if(this.memberRepository.existsById(id)){
            this.memberRepository.deleteById(id);
        }
        else{
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Member { id : "+id+" }");
        }
    }
}
