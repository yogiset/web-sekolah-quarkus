package resource;

import dto.AgendaData;
import entity.Agenda;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.AgendaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("agenda")
public class AgendaResource {
    @Inject
    private AgendaRepository agendaRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AgendaData> getAgenda(){
        List<Agenda>agendaList= agendaRepository.listAll();

        return agendaList.stream()
                .map(item -> new AgendaData(item.getId(),item.getJudul(), item.getTanggal(), item.getTanggals(),item.getTempat(),item.getDeskripsi()))
                .collect(Collectors.toList());

    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAgenda(AgendaData agendaData){
            Agenda agenda = new Agenda();
            agenda.setJudul(agendaData.judul());
            agenda.setTanggal(agendaData.tanggal());
            agenda.setTanggals(agendaData.tanggals());
            agenda.setTempat(agendaData.tempat());
            agenda.setDeskripsi(agendaData.deskripsi());

            agendaRepository.persist(agenda);
            return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAgenda(@PathParam("id")Long id, AgendaData agendaData){
        Optional<Agenda>agendaOptional = agendaRepository.findByIdOptional(id);
        if(agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            agenda.setJudul(agendaData.judul());
            agenda.setTanggal(agendaData.tanggal());
            agenda.setTanggals(agendaData.tanggals());
            agenda.setTempat(agendaData.tempat());
            agenda.setDeskripsi(agendaData.deskripsi());

            agendaRepository.persist(agenda);
            return Response.ok().build();
        }
            return  Response.status(Response.Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAgenda(@PathParam("id")Long id){
        Optional<Agenda>agendaOptional = agendaRepository.findByIdOptional(id);
        if(agendaOptional.isPresent()){
            agendaRepository.delete(agendaOptional.get());
            return Response.ok().build();
        }
            return Response.status(Response.Status.NOT_FOUND).build();
    }


}
