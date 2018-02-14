import { BaseEntity } from './../../shared';

export class Business implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public phone?: string,
        public email?: string,
        public fax?: string,
        public country?: string,
        public city?: string,
        public postalCode?: string,
        public address1?: string,
        public address2?: string,
        public lat?: number,
        public lng?: number,
    ) {
    }
}
