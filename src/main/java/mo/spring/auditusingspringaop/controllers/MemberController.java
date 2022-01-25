package mo.spring.auditusingspringaop.controllers;

import mo.spring.auditusingspringaop.entities.Address;
import mo.spring.auditusingspringaop.entities.Member;
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

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final IMemberService memberService;
    private final IAddressService addressService;

    @Autowired
    public MemberController(IMemberService memberService, IAddressService addressService) {
        this.memberService = memberService;
        this.addressService = addressService;
    }

    @GetMapping()
    public List<Member> getAllMembers() {
        return memberService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable(value = "id") Long memberId) {
        Member member = memberService.findById(memberId);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping()
    public Member createMember(@RequestBody Member member) {
        Member mem = memberService.save(member);
        for (Address a : mem.getAddresses()) {
            a.setMember(mem);
            addressService.save(a);
        }

        return mem;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable(value = "id") Long memberId, @RequestBody Member memberDetails) {
        Member member = memberService.findById(memberId);

        for (Address a : memberDetails.getAddresses()) {
            a.setMember(member);
            addressService.save(a);
        }

        member.setEmailId(memberDetails.getEmailId());
        member.setLastName(memberDetails.getLastName());
        member.setFirstName(memberDetails.getFirstName());
        final Member updatedMember = memberService.save(member);

        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMember(@PathVariable(value = "id") Long memberId){
        memberService.deleteById(memberId);
        return ResponseEntity.ok("Deleted");
    }
}
