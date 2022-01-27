package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.auditing.annotations.AfterInsert;
import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.dto.mapper.IMapper;
import mo.spring.auditusingspringaop.entities.Address;
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
    private IMapper mapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDTO findById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if(memberOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Member { id : "+id+" }");
        }

        return mapper.map(memberOptional.get(), MemberDTO.class);
    }

    @Override
    public List<MemberDTO> findAll() {
        return mapper.mapList(memberRepository.findAll(), MemberDTO.class);
    }

    @Override
    @AfterInsert(targetClass = Address.class, targetMethodName = "get", returnClass = Member.class)
    public MemberDTO save(MemberDTO dto) {
        return mapper.map(
                memberRepository.save(mapper.map(dto, Member.class)),
                MemberDTO.class
        );
    }

    @Override
    public MemberDTO update(MemberDTO dto) {
        return mapper.map(
                memberRepository.save(mapper.map(dto, Member.class)),
                MemberDTO.class
        );
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
