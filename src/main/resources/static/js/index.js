bookApi = Vue.resource("/api/books");

Vue.component('book-list', {
    props: ['books'],
    template: '<ul> <book-item v-for="book in books" v-bind:book="book"> {{ book.author}} : {{ book.title }} </book-item> </ul>'
});


Vue.component('book-item', {

    props: ['book'],
    template: '<li> {{ book.author }} : {{ book.title }} </li>'

});

app = new Vue({

    el: "#app",
    data: {
        books: []
    },
    created: function () {
        bookApi.get().then(result =>
            result.body.forEach(book => this.books.push(book))
        )
    }

});