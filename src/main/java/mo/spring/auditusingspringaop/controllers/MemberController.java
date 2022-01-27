package mo.spring.auditusingspringaop.controllers;

import mo.spring.auditusingspringaop.dto.AddressDTO;
import mo.spring.auditusingspringaop.dto.MemberDTO;
import mo.spring.auditusingspringaop.dto.mapper.IMapper;
import mo.spring.auditusingspringaop.services.IAddressService;
import mo.spring.auditusingspringaop.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final IMemberService memberService;
    private final IAddressService addressService;

    @Autowired
    private IMapper mapper;

    @Autowired
    public MemberController(IMemberService memberService, IAddressService addressService) {
        this.memberService = memberService;
        this.addressService = addressService;
    }

    @GetMapping()
    public List<MemberDTO> getAllMembers() {
        return memberService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable(value = "id") Long memberId) {
        MemberDTO memberDTO = memberService.findById(memberId);
        return ResponseEntity.ok().body(memberDTO);
    }

    @PostMapping()
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO mem = memberService.save(memberDTO);
        Set<AddressDTO> addressess = new HashSet<AddressDTO>();
        for (AddressDTO a : mem.getAddresses()) {
            a.setMember(mem);
            a = addressService.save(a);
            addressess.add(a);
        }
        mem.setAddresses(addressess);

        return ResponseEntity.ok(mem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable(value = "id") Long memberId, @RequestBody MemberDTO memberDetails) {
        MemberDTO memberDTO = memberService.findById(memberId);

        Set<AddressDTO> addressess = new HashSet<AddressDTO>();
        if(memberDetails.getAddresses() != null){
            memberDTO.getAddresses().forEach(ad ->
                    addressService.deleteById(ad.getId())
            );
        }

        if(memberDetails.getAddresses() != null){
            for (AddressDTO a : memberDetails.getAddresses()) {
                a.setMember(memberDTO);
                a = addressService.save(a);
                addressess.add(a);
            }
            memberDTO.setAddresses(addressess);
        }

//        for (AddressDTO a : memberDetails.getAddresses()) {
//            a.setMember(memberDTO);
//            a = addressService.save(a);
//            addressess.add(a);
//        }
//        memberDTO.setAddresses(addressess);

        memberDTO.setEmailId(memberDetails.getEmailId());
        memberDTO.setLastName(memberDetails.getLastName());
        memberDTO.setFirstName(memberDetails.getFirstName());
        MemberDTO updatedMember = memberService.save(memberDTO);

        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMember(@PathVariable(value = "id") Long memberId){
        memberService.deleteById(memberId);
        return ResponseEntity.ok("Deleted");
    }
}
