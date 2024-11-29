import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-cust-wq.reducer';

export const FimCustWqDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimCustWqEntity = useAppSelector(state => state.fim.fimCustWq.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimCustWqDetailsHeading">
          <Translate contentKey="fimApp.fimFimCustWq.detail.title">FimCustWq</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.id}</dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimCustWq.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.custId}</dd>
          <dt>
            <span id="clientId">
              <Translate contentKey="fimApp.fimFimCustWq.clientId">Client Id</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.clientId}</dd>
          <dt>
            <span id="idType">
              <Translate contentKey="fimApp.fimFimCustWq.idType">Id Type</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.idType}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimCustWq.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.ctryCode}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimCustWq.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimCustWq.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimCustWqEntity.createdTs ? <TextFormat value={fimCustWqEntity.createdTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimCustWq.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimCustWq.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimCustWqEntity.updatedTs ? <TextFormat value={fimCustWqEntity.updatedTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimCustWq.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.recordStatus}</dd>
          <dt>
            <span id="uploadRemark">
              <Translate contentKey="fimApp.fimFimCustWq.uploadRemark">Upload Remark</Translate>
            </span>
          </dt>
          <dd>{fimCustWqEntity.uploadRemark}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-cust-wq" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-cust-wq/${fimCustWqEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimCustWqDetail;
