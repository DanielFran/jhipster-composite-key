import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BasicIndex } from './basic-index.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BasicIndex>;

@Injectable()
export class BasicIndexService {

    private resourceUrl =  SERVER_API_URL + 'api/basic-indices';

    constructor(private http: HttpClient) { }

    create(basicIndex: BasicIndex): Observable<EntityResponseType> {
        const copy = this.convert(basicIndex);
        return this.http.post<BasicIndex>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(basicIndex: BasicIndex): Observable<EntityResponseType> {
        const copy = this.convert(basicIndex);
        return this.http.put<BasicIndex>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BasicIndex>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BasicIndex[]>> {
        const options = createRequestOption(req);
        return this.http.get<BasicIndex[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BasicIndex[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BasicIndex = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BasicIndex[]>): HttpResponse<BasicIndex[]> {
        const jsonResponse: BasicIndex[] = res.body;
        const body: BasicIndex[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BasicIndex.
     */
    private convertItemFromServer(basicIndex: BasicIndex): BasicIndex {
        const copy: BasicIndex = Object.assign({}, basicIndex);
        return copy;
    }

    /**
     * Convert a BasicIndex to a JSON which can be sent to the server.
     */
    private convert(basicIndex: BasicIndex): BasicIndex {
        const copy: BasicIndex = Object.assign({}, basicIndex);
        return copy;
    }
}
