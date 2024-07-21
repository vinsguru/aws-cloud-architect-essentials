/*
    Just a simple js file for a quick demo purposes.
*/
const recommendedNav = document.getElementById('recommended-nav');
const moviesList = document.querySelector('#movies-list tbody');
const allMoviesNav = document.getElementById('all-movies-nav');
const customerInfo = document.getElementById('customer-info');
const customerNotFound = document.getElementById('customer-not-found');

customerNotFound.style.display = 'none';

recommendedNav.addEventListener('click', () => fetchCustomer());
allMoviesNav.addEventListener('click', () => fetchAllMovies());

// get customer id or assume 1
const params = new URLSearchParams(window.location.search);
let customerId;
if(params.get('customer')){
  customerId = params.get('customer');
}else{
  customerId = 1;
}

const fetchAllMovies = async () => {
  const uri = `/api/movies`;
  const response = await fetch(uri);
  const movies = await response.json();
  loadMovies(movies);
  activateElement(allMoviesNav);
}; 

const fetchCustomer = async () => {
  const uri = `/api/customers/${customerId}`;
  try{
    const response = await fetch(uri);
    const data = await response.json();
    document.getElementById('customer-name').innerText = data.name;
    document.getElementById('customer-genre').innerText = data.favoriteGenre;
    document.getElementById('customer-image').src = `images\\customer-${data.id}.png`;
    loadMovies(data.recommendedMovies);
    activateElement(recommendedNav);
  }catch(error){
    showCustomerNotFound(`Customer ${customerId} is not found`)
  }

}; 

const loadMovies = (movies) => {
  moviesList.replaceChildren();
  movies.forEach((movie, index) => addMovieToTable(movie, index + 1));
}; 

const addMovieToTable = (movie, index) => {
  const template = `
      <th scope="row">${index}</th>
      <td class="text-capitalize">${movie.title}</td>
      <td class="text-capitalize">${movie.releaseYear}</td>
      <td>${movie.genre}</td>               
  `;
  const row = document.createElement('tr');
  row.innerHTML = template;
  moviesList.appendChild(row);
};

const activateElement = (ele) => {
  document.querySelector("nav > a.active").classList.remove("active", "disabled");
  ele.classList.add("active", "disabled");
}

const showCustomerNotFound = (message) => {
  customerInfo.style.display = 'none';
  customerNotFound.style.display = 'block';
  document.getElementById('error-message').innerText = message;
}

const changeCustomerGenre = async (favoriteGenre) => {
  const uri = `/api/customers/${customerId}/genre`;
  const requestBody = { favoriteGenre };
  await fetch(uri, {
    method: 'PATCH',
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(requestBody)
  });
  await fetchCustomer();
}

window.addEventListener('load', () => fetchCustomer());