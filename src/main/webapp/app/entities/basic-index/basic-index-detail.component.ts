import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BasicIndex } from './basic-index.model';
import { BasicIndexService } from './basic-index.service';

@Component({
    selector: 'jhi-basic-index-detail',
    templateUrl: './basic-index-detail.component.html'
})
export class BasicIndexDetailComponent implements OnInit, OnDestroy {

    basicIndex: BasicIndex;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private basicIndexService: BasicIndexService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBasicIndices();
    }

    load(id) {
        this.basicIndexService.find(id)
            .subscribe((basicIndexResponse: HttpResponse<BasicIndex>) => {
                this.basicIndex = basicIndexResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBasicIndices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'basicIndexListModification',
            (response) => this.load(this.basicIndex.id)
        );
    }
}
