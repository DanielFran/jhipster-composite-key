import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BasicIndex } from './basic-index.model';
import { BasicIndexService } from './basic-index.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-basic-index',
    templateUrl: './basic-index.component.html'
})
export class BasicIndexComponent implements OnInit, OnDestroy {
basicIndices: BasicIndex[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private basicIndexService: BasicIndexService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.basicIndexService.query().subscribe(
            (res: HttpResponse<BasicIndex[]>) => {
                this.basicIndices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInBasicIndices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: BasicIndex) {
        return item.id;
    }
    registerChangeInBasicIndices() {
        this.eventSubscriber = this.eventManager.subscribe('basicIndexListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
