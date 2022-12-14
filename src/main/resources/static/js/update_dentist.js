window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_dentist_form');

    formulario.addEventListener('submit', function (e) {
    e.preventDefault();

        const formData = {
            id: document.querySelector('#dentist_id').value,
            matricula: document.querySelector('#license').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value

        };


        const url = '/dentists';
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
                      window.location.replace("/list_dentist.html");
                  })


    })
 })

function findBy(id) {
          const url = '/dentists'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let dentist = data;
              console.log(dentist)
              document.querySelector('#dentist_id').value = id;
              document.querySelector('#license').value = dentist.matricula;
              document.querySelector('#nombre').value= dentist.nombre;
              document.querySelector('#apellido').value= dentist.apellido;


              document.querySelector('#update_dentist_form').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }