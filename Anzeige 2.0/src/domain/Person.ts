export class Person {
    id: number;
    name: string;
    imageUrl: string;

    constructor(id: number, name: string, avatar: string) {
        this.id = id;
        this.name = name;
        this.imageUrl = avatar;
    }
}