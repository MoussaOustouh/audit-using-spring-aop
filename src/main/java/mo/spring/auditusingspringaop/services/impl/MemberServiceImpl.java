package mo.spring.auditusingspringaop.services.impl;

import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterDelete;
import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterCreate;
import mo.spring.auditusingspringaop.traceability_services.impl.TraceMemberServiceImpl;
import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.dto.mapper.IMapper;
import mo.spring.auditusingspringaop.entities.Member;

import mo.spring.auditusingspringaop.exceptions.NotFoundException;
import mo.spring.auditusingspringaop.exceptions.constants.ErrorMessages;
import mo.spring.auditusingspringaop.repositories.MemberRepository;
import mo.spring.auditusingspringaop.services.IMemberService;
import mo.spring.auditusingspringaop.traceability.strategy.annotations.TraceAfterUpdate;
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
    @TraceAfterCreate(
            targetServiceClass = TraceMemberServiceImpl.class,
            targetMethodName = "traceAfterCreate",
            targetMethodArgsClasses = {MemberDTO.class, String.class, String.class},
            actionInfo = "trace after create description"
    )
    public MemberDTO save(MemberDTO dto) {
        return mapper.map(
                memberRepository.save(mapper.map(dto, Member.class)),
                MemberDTO.class
        );
    }

    @Override
    @TraceAfterUpdate(
            targetServiceClass = TraceMemberServiceImpl.class,
            targetMethodName = "traceAfterUpdate",
            targetMethodArgsClasses = {MemberDTO.class, String.class, String.class},
            actionInfo = "trace after update description"
    )
    public MemberDTO update(MemberDTO dto) {
        return mapper.map(
                memberRepository.save(mapper.map(dto, Member.class)),
                MemberDTO.class
        );
    }

    @Override
    @TraceAfterDelete(
            targetServiceClass = TraceMemberServiceImpl.class,
            targetMethodName = "traceAfterDelete",
            targetMethodArgsClasses = {MemberDTO.class, String.class, String.class, Object[].class},
            actionInfo = "trace after delete description"
    )
    public void deleteById(Long id) {
        if(this.memberRepository.existsById(id)){
            this.memberRepository.deleteById(id);
        }
        else{
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " Member { id : "+id+" }");
        }
    }
}
