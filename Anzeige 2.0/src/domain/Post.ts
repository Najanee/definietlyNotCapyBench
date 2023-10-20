import { Person } from "./Person";

export class Post {
    id: number;
    title: string;
    content: string;
    author: Person;
    createdDate: Date;

    constructor(id: number, title: string, 
                content: string, author: Person, 
                createdDate: Date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = createdDate;
    }
}