/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { BusinessBasicIndexDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index-delete-dialog.component';
import { BusinessBasicIndexService } from '../../../../../../main/webapp/app/entities/business-basic-index/business-basic-index.service';

describe('Component Tests', () => {

    describe('BusinessBasicIndex Management Delete Component', () => {
        let comp: BusinessBasicIndexDeleteDialogComponent;
        let fixture: ComponentFixture<BusinessBasicIndexDeleteDialogComponent>;
        let service: BusinessBasicIndexService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BusinessBasicIndexDeleteDialogComponent],
                providers: [
                    BusinessBasicIndexService
                ]
            })
            .overrideTemplate(BusinessBasicIndexDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BusinessBasicIndexDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessBasicIndexService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
