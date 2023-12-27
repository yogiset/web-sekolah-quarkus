package resource;

import dto.StaffData;
import entity.Staff;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.StaffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("staff")
public class StaffResource {
    @Inject
    private StaffRepository staffRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<StaffData>getStaff(){
        List<Staff>staffList = staffRepository.listAll();

        return staffList.stream()
                .map(item -> new StaffData(item.getId(),item.getNama(),item.getNip(),item.getJabatan(),item.getAlamat(),item.getTgl_lahir(),item.getUmur(),item.getFoto()))
                .collect(Collectors.toList());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStaff(StaffData staffData){
        Staff staff = new Staff();
        staff.setNama(staffData.nama());
        staff.setNip(staffData.nip());
        staff.setJabatan(staffData.jabatan());
        staff.setAlamat(staffData.alamat());
        staff.setTgl_lahir(staffData.tgl_lahir());
        staff.setFoto(staffData.foto());

        staffRepository.persist(staff);

        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(@PathParam("id")Long id, StaffData staffData){
        Optional<Staff>optionalStaff = staffRepository.findByIdOptional(id);

        if(optionalStaff.isPresent()){
            Staff staff = optionalStaff.get();
            staff.setNama(staffData.nama());
            staff.setNip(staffData.nip());
            staff.setJabatan(staffData.jabatan());
            staff.setAlamat(staffData.alamat());
            staff.setTgl_lahir(staffData.tgl_lahir());
            staff.setFoto(staffData.foto());

            staffRepository.persist(staff);

            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStaff(@PathParam("id")Long id){
        Optional<Staff>optionalStaff = staffRepository.findByIdOptional(id);
        if (optionalStaff.isPresent()){
            staffRepository.delete(optionalStaff.get());

            return Response.ok().build();
        }

        return  Response.status(Response.Status.NOT_FOUND).build();
    }
}
