window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_appointment_form');

    formulario.addEventListener('submit', function (e) {
    e.preventDefault();

        const formData = {
            id: document.querySelector('#appointment_id').value,
            patientId: document.querySelector('#patient_id').value,
            dentistId: document.querySelector('#dentist_id').value,
            fecha: document.querySelector('#appointmentFecha').value
        };


        const url = '/appointments';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => {
                      if (response.status == 404){
                          alert("Las modificaciones no fueron ejecutadas");
                      }
                      window.location.replace("/get_appointments.html");
                  })


    })
 })

function findBy(id) {
          const url = '/appointments/buscar'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              console.log(turno)
              document.querySelector('#appointment_id').value = id;
              document.querySelector('#patient_id').value=turno.patientId;
              document.querySelector('#dentist_id').value=turno.dentistId;
              document.querySelector('#appointmentFecha').value=turno.fecha;

              document.querySelector('#update_appointment_form').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }




