bookApi = Vue.resource("/api/books");

Vue.component("book-list", {
    props: ["books"],
    template: "<ul> <book-item v-for='book in books' v-bind:book='book'> </book-item> </ul>",
});


Vue.component("book-item", {
    props: ["book"],
    template: "<li v-bind:key='book.id'> <b> {{ book.author }}: </b> {{ book.title }} </li>"
});

app = new Vue({

    el: "#app",
    template:'<book-list v-bind:books="books"/>',
    data: {
        books: [],
    },
    created: function() {
        bookApi.get().then(result =>
                result.body.forEach(book => this.books.push(book))
        )
    }
});

