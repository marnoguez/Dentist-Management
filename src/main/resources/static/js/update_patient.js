window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_patient_form');

    formulario.addEventListener('submit', function (event) {
        let patientId = document.querySelector('#patient_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#patient_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            email: document.querySelector('#email').value,
            domicilio:{
                            id: document.querySelector('#domicilio_id').value,
                            calle: document.querySelector('#calle').value,
                            numero: document.querySelector('#numero').value,
                            localidad: document.querySelector('#localidad').value,
                            provincia: document.querySelector('#provincia').value,
                            }

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/patient';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

function findBy(id) {
          const url = '/patient'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let patient = data;
              document.querySelector('#patient_id').value = patient.id;
              document.querySelector('#nombre').value=patient.nombre;
              document.querySelector('#apellido').value=patient.apellido;
              document.querySelector('#dni').value=patient.dni;
              document.querySelector('#fechaIngreso').value=patient.fechaIngreso;
              document.querySelector('#email').value=patient.email;
              document.querySelector('#domicilio_id').value=patient.domicilio.id;
              document.querySelector('#calle').value=patient.domicilio.calle;
              document.querySelector('#numero').value=patient.domicilio.numero;
              document.querySelector('#localidad').value=patient.domicilio.localidad;
              document.querySelector('#provincia').value=patient.domicilio.provincia;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_patient_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }

  function deleteBy(id)
  {
            //con fetch invocamos a la API de peliculas con el método DELETE
            //pasandole el id en la URL
            const url = '/patient/'+id;
            const settings = {
                method: 'DELETE'
            }
            fetch(url,settings)
            .then(response => response.json())

            //borrar la fila de la pelicula eliminada
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();

  }